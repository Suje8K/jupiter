"use client"

import React, { Suspense, useState, useEffect } from 'react';
import { Companions } from "@/components/companions";
import { Categories } from "@/components/categories";
import { SearchInput } from "@/components/search-input";

interface RootPageProps {
    searchParams: {
        categoryId?: string;
        name?: string;
    };
}

const RootPage = ({ searchParams }: RootPageProps) => {
    const [companions, setCompanions] = useState<any>({});
    const [categories, setCategories] = useState<any>({});
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const headers = {
        'Content-Type': 'application/json'
    };

    let url = process.env.NEXT_PUBLIC_BACKEND_URL || "";

    const fetchData = async () => {
        try {
            const queryString = new URLSearchParams(searchParams as any).toString();
            const response = await fetch(`${url}/companion?${queryString}`, { method: 'GET', headers });
            const contentType = response.headers.get('Content-Type');
            if (contentType && contentType.includes('application/json')) {
                const data = await response.json();
                setCompanions(data);
            } else {
                setCompanions([]);
            }
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            setError('Failed to fetch companions');
        }
    };

    const fetchCategories = async () => {
        try {
            const response = await fetch(`${url}/companion/categories`, { method: 'GET', headers });
            const contentType = response.headers.get('Content-Type');
            if (contentType && contentType.includes('application/json')) {
                const data = await response.json();
                setCategories(data);
            } else {
                setCategories([]);
            }
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            setError('Failed to fetch categories');
        }
    };

    useEffect(() => {
        const fetchDataAndCategories = async () => {
            setLoading(true);
            await Promise.all([fetchData(), fetchCategories()]);
            setLoading(false);
        };

        fetchDataAndCategories();
    }, [searchParams]);

    return (
        <Suspense fallback={<div>Loading components...</div>}>
            <div className="h-full p-4 space-y-2">
                <SearchInput/>
                <Categories data={categories.data}/>
                <Companions data={companions.data}/>
            </div>
        </Suspense>
    );
};

export default RootPage;
